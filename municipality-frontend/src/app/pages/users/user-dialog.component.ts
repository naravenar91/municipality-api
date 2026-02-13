import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { User } from '../../core/models';

@Component({
  selector: 'app-user-dialog',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  template: `
    <h2 mat-dialog-title>Crear Usuario</h2>
    <mat-dialog-content>
      <form [formGroup]="form" class="dialog-form">
        <mat-form-field class="full-width">
          <mat-label>RUT</mat-label>
          <input matInput formControlName="userName" placeholder="Ej: 111111111">
          @if (form.get('userName')?.hasError('required') && form.get('userName')?.touched) {
            <mat-error>El RUT es requerido</mat-error>
          }
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Nombre</mat-label>
          <input matInput formControlName="name" placeholder="Nombre completo">
          @if (form.get('name')?.hasError('required') && form.get('name')?.touched) {
            <mat-error>El nombre es requerido</mat-error>
          }
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Email</mat-label>
          <input matInput formControlName="email" type="email" placeholder="correo@ejemplo.com">
          @if (form.get('email')?.hasError('email') && form.get('email')?.touched) {
            <mat-error>Email inválido</mat-error>
          }
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Dirección</mat-label>
          <input matInput formControlName="address" placeholder="Ej: casa 123">
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Teléfono</mat-label>
          <input matInput formControlName="phone" placeholder="+56911111111">
        </mat-form-field>
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
    }
  `],
})
export class UserDialogComponent implements OnInit {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<UserDialogComponent>);
  data = inject<User | null>(MAT_DIALOG_DATA);

  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      userName: ['', Validators.required],
      name: ['', Validators.required],
      email: ['', Validators.email],
      address: [''],
      phone: [''],
    });
  }

  save(): void {
    if (this.form.valid) {
      this.dialogRef.close(this.form.value);
    }
  }
}
