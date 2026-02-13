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
  templateUrl: './municipality-dialog.component.html',
  styleUrl: './municipality-dialog.component.scss',
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
