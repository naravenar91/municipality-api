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
  template: `
    <div class="page-container">
      <div class="page-header">
        <h1>Municipalidades</h1>
        <button mat-fab extended color="primary" (click)="openDialog()">
          <mat-icon>add</mat-icon>
          Nueva Municipalidad
        </button>
      </div>

      @if (loading) {
        <div class="spinner-container">
          <mat-spinner diameter="48"></mat-spinner>
        </div>
      } @else {
        <table mat-table [dataSource]="municipalities" class="mat-elevation-z2">
          <ng-container matColumnDef="id">
            <th mat-header-cell *matHeaderCellDef>ID</th>
            <td mat-cell *matCellDef="let row">{{ row.id }}</td>
          </ng-container>

          <ng-container matColumnDef="name">
            <th mat-header-cell *matHeaderCellDef>Nombre</th>
            <td mat-cell *matCellDef="let row">{{ row.name }}</td>
          </ng-container>

          <ng-container matColumnDef="address">
            <th mat-header-cell *matHeaderCellDef>Dirección</th>
            <td mat-cell *matCellDef="let row">{{ row.address }}</td>
          </ng-container>

          <ng-container matColumnDef="region">
            <th mat-header-cell *matHeaderCellDef>Región</th>
            <td mat-cell *matCellDef="let row">{{ row.region }}</td>
          </ng-container>

          <ng-container matColumnDef="createdAt">
            <th mat-header-cell *matHeaderCellDef>Creado</th>
            <td mat-cell *matCellDef="let row">{{ row.createdAt }}</td>
          </ng-container>

          <ng-container matColumnDef="actions">
            <th mat-header-cell *matHeaderCellDef class="actions-cell">Acciones</th>
            <td mat-cell *matCellDef="let row" class="actions-cell">
              <button mat-icon-button color="primary" (click)="openDialog(row)">
                <mat-icon>edit</mat-icon>
              </button>
            </td>
          </ng-container>

          <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
          <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>

          <tr class="mat-row" *matNoDataRow>
            <td class="mat-cell" [attr.colspan]="displayedColumns.length" style="text-align:center; padding:24px;">
              No hay municipalidades registradas
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
