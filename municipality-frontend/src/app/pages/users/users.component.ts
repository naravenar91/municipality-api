import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { User } from '../../core/models';
import { UserService } from '../../core/services/user.service';
import { UserDialogComponent } from './user-dialog.component';

@Component({
  selector: 'app-users',
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
        <h1>Usuarios</h1>
        <button mat-fab extended color="primary" (click)="openDialog()">
          <mat-icon>person_add</mat-icon>
          Nuevo Usuario
        </button>
      </div>

      @if (loading) {
        <div class="spinner-container">
          <mat-spinner diameter="48"></mat-spinner>
        </div>
      } @else {
        <table mat-table [dataSource]="users" class="mat-elevation-z2">
          <ng-container matColumnDef="id">
            <th mat-header-cell *matHeaderCellDef>ID</th>
            <td mat-cell *matCellDef="let row">{{ row.id }}</td>
          </ng-container>

          <ng-container matColumnDef="userName">
            <th mat-header-cell *matHeaderCellDef>RUT</th>
            <td mat-cell *matCellDef="let row">{{ row.userName }}</td>
          </ng-container>

          <ng-container matColumnDef="name">
            <th mat-header-cell *matHeaderCellDef>Nombre</th>
            <td mat-cell *matCellDef="let row">{{ row.name }}</td>
          </ng-container>

          <ng-container matColumnDef="email">
            <th mat-header-cell *matHeaderCellDef>Email</th>
            <td mat-cell *matCellDef="let row">{{ row.email }}</td>
          </ng-container>

          <ng-container matColumnDef="phone">
            <th mat-header-cell *matHeaderCellDef>Teléfono</th>
            <td mat-cell *matCellDef="let row">{{ row.phone }}</td>
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
              No hay usuarios registrados
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
export class UsersComponent implements OnInit {
  private service = inject(UserService);
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);

  users: User[] = [];
  loading = true;
  displayedColumns = ['id', 'userName', 'name', 'email', 'phone', 'isActive', 'createdAt'];

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.service.getAll().subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
      },
      error: () => {
        this.snackBar.open('Error al cargar usuarios', 'Cerrar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(UserDialogComponent, {
      data: null,
      width: '500px',
    });

    dialogRef.afterClosed().subscribe((result: User | undefined) => {
      if (result) {
        this.service.create(result).subscribe({
          next: () => {
            this.snackBar.open('Usuario creado', 'OK', { duration: 3000 });
            this.load();
          },
          error: () => {
            this.snackBar.open('Error al crear usuario', 'Cerrar', { duration: 3000 });
          },
        });
      }
    });
  }
}
