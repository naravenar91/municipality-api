import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { forkJoin } from 'rxjs';
import { ServiceAssociation, Municipality, User } from '../../core/models';
import { ServiceService } from '../../core/services/service.service';
import { MunicipalityService } from '../../core/services/municipality.service';
import { UserService } from '../../core/services/user.service';
import { ServiceDialogComponent, ServiceDialogData } from './service-dialog.component';

@Component({
  selector: 'app-services',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatChipsModule,
  ],
  templateUrl: './services.component.html',
  styleUrl: './services.component.scss',
})
export class ServicesComponent implements OnInit {
  private serviceService = inject(ServiceService);
  private municipalityService = inject(MunicipalityService);
  private userService = inject(UserService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  services: ServiceAssociation[] = [];
  municipalities: Municipality[] = [];
  users: User[] = [];
  loading = true;
  displayedColumns = ['userId', 'muniId', 'isActive', 'createdAt'];

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    forkJoin({
      services: this.serviceService.getAll(),
      municipalities: this.municipalityService.getAll(),
      users: this.userService.getAll(),
    }).subscribe({
      next: (data) => {
        this.services = data.services;
        this.municipalities = data.municipalities;
        this.users = data.users;
        this.loading = false;
      },
      error: () => {
        this.snackBar.open('Error al cargar datos', 'Cerrar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  openDialog(): void {
    const dialogData: ServiceDialogData = {
      municipalities: this.municipalities,
      users: this.users,
    };

    const dialogRef = this.dialog.open(ServiceDialogComponent, {
      data: dialogData,
      width: '500px',
    });

    dialogRef.afterClosed().subscribe((result: ServiceAssociation | undefined) => {
      if (result) {
        this.serviceService.create(result).subscribe({
          next: () => {
            this.snackBar.open('Servicio asociado correctamente', 'OK', { duration: 3000 });
            this.load();
          },
          error: () => {
            this.snackBar.open('Error al asociar servicio', 'Cerrar', { duration: 3000 });
          },
        });
      }
    });
  }
}
