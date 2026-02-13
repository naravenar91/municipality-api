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
  template: `
    <div class="page-container">
      <div class="page-header">
        <h1>Servicios</h1>
        <button mat-fab extended color="primary" (click)="openDialog()">
          <mat-icon>link</mat-icon>
          Nueva Asociación
        </button>
      </div>

      @if (loading) {
        <div class="spinner-container">
          <mat-spinner diameter="48"></mat-spinner>
        </div>
      } @else {
        <table mat-table [dataSource]="services" class="mat-elevation-z2">
          <ng-container matColumnDef="userId">
            <th mat-header-cell *matHeaderCellDef>ID Usuario</th>
            <td mat-cell *matCellDef="let row">{{ row.userId }}</td>
          </ng-container>

          <ng-container matColumnDef="muniId">
            <th mat-header-cell *matHeaderCellDef>ID Municipalidad</th>
            <td mat-cell *matCellDef="let row">{{ row.muniId }}</td>
          </ng-container>

          <ng-container matColumnDef="isActive">
            <th mat-header-cell *matHeaderCellDef>Estado</th>
            <td mat-cell *matCellDef="let row">
              <mat-chip [highlighted]="row.isActive" [color]="row.isActive ? 'primary' : 'warn'">
                {{ row.isActive ? 'Activo' : 'Inactivo' }}
              </mat-chip>
            </td>
          </ng-container>

          <ng-container matColumnDef="createdAt">
            <th mat-header-cell *matHeaderCellDef>Creado</th>
            <td mat-cell *matCellDef="let row">{{ row.createdAt }}</td>
          </ng-container>

          <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
          <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>

          <tr class="mat-row" *matNoDataRow>
            <td class="mat-cell" [attr.colspan]="displayedColumns.length" style="text-align:center; padding:24px;">
              No hay servicios registrados
            </td>
          </tr>
        </table>
      }
    </div>
  `,
  styles: [`
    .spinner-container {
      display: flex;
      justify-content: center;
      padding: 48px;
    }
  `],
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
