import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { Municipality } from '../../core/models';
import { MunicipalityService } from '../../core/services/municipality.service';
import { MunicipalityDialogComponent } from './municipality-dialog.component';

@Component({
  selector: 'app-municipalities',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatDialogModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './municipalities.component.html',
  styleUrl: './municipalities.component.scss',
})
export class MunicipalitiesComponent implements OnInit {
  private service = inject(MunicipalityService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  municipalities: Municipality[] = [];
  loading = true;
  displayedColumns = ['id', 'name', 'address', 'region', 'createdAt', 'actions'];

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.service.getAll().subscribe({
      next: (data) => {
        this.municipalities = data;
        this.loading = false;
      },
      error: () => {
        this.snackBar.open('Error al cargar municipalidades', 'Cerrar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  openDialog(municipality?: Municipality): void {
    const dialogRef = this.dialog.open(MunicipalityDialogComponent, {
      data: municipality ?? null,
      width: '500px',
    });

    dialogRef.afterClosed().subscribe((result: Municipality | undefined) => {
      if (result) {
        const op = result.id
          ? this.service.update(result)
          : this.service.create(result);

        op.subscribe({
          next: () => {
            this.snackBar.open(
              result.id ? 'Municipalidad actualizada' : 'Municipalidad creada',
              'OK',
              { duration: 3000 },
            );
            this.load();
          },
          error: () => {
            this.snackBar.open('Error al guardar', 'Cerrar', { duration: 3000 });
          },
        });
      }
    });
  }
}
