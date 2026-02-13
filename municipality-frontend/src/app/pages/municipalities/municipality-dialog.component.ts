import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Municipality } from '../../core/models';

@Component({
  selector: 'app-municipality-dialog',
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
    <h2 mat-dialog-title>{{ data ? 'Editar' : 'Crear' }} Municipalidad</h2>
    <mat-dialog-content>
      <form [formGroup]="form" class="dialog-form">
        <mat-form-field class="full-width">
          <mat-label>Nombre</mat-label>
          <input matInput formControlName="name" placeholder="Ej: Concepción">
          @if (form.get('name')?.hasError('required') && form.get('name')?.touched) {
            <mat-error>El nombre es requerido</mat-error>
          }
          @if (form.get('name')?.hasError('minlength') && form.get('name')?.touched) {
            <mat-error>Mínimo 3 caracteres</mat-error>
          }
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Dirección</mat-label>
          <input matInput formControlName="address" placeholder="Dirección de la municipalidad">
        </mat-form-field>

        <mat-form-field class="full-width">
          <mat-label>Región (ID)</mat-label>
          <input matInput formControlName="region" type="number" placeholder="Ej: 8">
          @if (form.get('region')?.hasError('required') && form.get('region')?.touched) {
            <mat-error>La región es requerida</mat-error>
          }
        </mat-form-field>
      </form>
    </mat-dialog-content>
    <mat-dialog-actions align="end">
      <button mat-button mat-dialog-close>Cancelar</button>
      <button mat-flat-button color="primary" (click)="save()" [disabled]="form.invalid">
        {{ data ? 'Actualizar' : 'Crear' }}
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
export class MunicipalityDialogComponent implements OnInit {
  private fb = inject(FormBuilder);
  private dialogRef = inject(MatDialogRef<MunicipalityDialogComponent>);
  data = inject<Municipality | null>(MAT_DIALOG_DATA);

  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      name: [this.data?.name ?? '', [Validators.required, Validators.minLength(3)]],
      address: [this.data?.address ?? ''],
      region: [this.data?.region ?? null, Validators.required],
    });
  }

  save(): void {
    if (this.form.valid) {
      const result: Municipality = {
        ...this.form.value,
      };
      if (this.data?.id) {
        result.id = this.data.id;
      }
      this.dialogRef.close(result);
    }
  }
}
