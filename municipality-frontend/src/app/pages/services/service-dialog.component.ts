import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Municipality, User } from '../../core/models';

export interface ServiceDialogData {
  municipalities: Municipality[];
  users: User[];
}

@Component({
  selector: 'app-service-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatSlideToggleModule,
  ],
  template: `
    <h2 mat-dialog-title>Asociar Servicio</h2>
    <mat-dialog-content>
      <form [formGroup]="form" class="dialog-form">
        <mat-form-field class="full-width">
          <mat-label>Usuario</mat-label>
          <mat-select formControlName="userId">
            @for (user of data.users; track user.id) {
              <mat-option [value]="user.id">{{ user.userName }} - {{ user.name }}</mat-option>
            }
          </mat-select>
          @if (form.get('userId')?.hasError('required') && form.get('userId')?.touched) {
            <mat-error>Seleccione un usuario</mat-error>
          }
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Municipalidad</mat-label>
          <mat-select formControlName="muniId">
            @for (mun of data.municipalities; track mun.id) {
              <mat-option [value]="mun.id">{{ mun.name }}</mat-option>
            }
          </mat-select>
          @if (form.get('muniId')?.hasError('required') && form.get('muniId')?.touched) {
            <mat-error>Seleccione una municipalidad</mat-error>
          }
        </mat-form-field>

        <mat-slide-toggle formControlName="isActive" color="primary">
          Activo
        </mat-slide-toggle>
      </form>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancelar</button>
      <button mat-flat-button color="primary" (click)="save()" [disabled]="form.invalid">
        Crear
      </button>
    </mat-dialog-actions>
  `,
  styles: [`
    .dialog-form {
      display: flex;
      flex-direction: column;
      min-width: 400px;
      padding-top: 8px;
      gap: 8px;
    }
  `],
})
export class ServiceDialogComponent implements OnInit {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<ServiceDialogComponent>);
  data = inject<ServiceDialogData>(MAT_DIALOG_DATA);

  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      userId: [null, Validators.required],
      muniId: [null, Validators.required],
      isActive: [true],
    });
  }

  save(): void {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }
}
