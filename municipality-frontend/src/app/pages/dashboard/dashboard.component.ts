import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { forkJoin } from 'rxjs';
import { MunicipalityService } from '../../core/services/municipality.service';
import { UserService } from '../../core/services/user.service';
import { ServiceService } from '../../core/services/service.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink, MatCardModule, MatIconModule, MatButtonModule],
  template: `
    <div class="page-container">
      <div class="page-header">
        <h1>Dashboard</h1>
      </div>

      <div class="card-grid">
        <mat-card class="stat-card" appearance="outlined">
          <mat-card-header>
            <mat-icon mat-card-avatar class="card-icon municipalities">location_city</mat-icon>
            <mat-card-title>Municipalidades</mat-card-title>
            <mat-card-subtitle>Total registradas</mat-card-subtitle>
          </mat-card-header>
          <mat-card-content>
            <div class="stat-value">{{ municipalityCount }}</div>
          </mat-card-content>
          <mat-card-actions>
            <a mat-button color="primary" routerLink="/municipalities">Ver todas</a>
          </mat-card-actions>
        </mat-card>

        <mat-card class="stat-card" appearance="outlined">
          <mat-card-header>
            <mat-icon mat-card-avatar class="card-icon users">people</mat-icon>
            <mat-card-title>Usuarios</mat-card-title>
            <mat-card-subtitle>Total registrados</mat-card-subtitle>
          </mat-card-header>
          <mat-card-content>
            <div class="stat-value">{{ userCount }}</div>
          </mat-card-content>
          <mat-card-actions>
            <a mat-button color="primary" routerLink="/users">Ver todos</a>
          </mat-card-actions>
        </mat-card>

        <mat-card class="stat-card" appearance="outlined">
          <mat-card-header>
            <mat-icon mat-card-avatar class="card-icon services">miscellaneous_services</mat-icon>
            <mat-card-title>Servicios</mat-card-title>
            <mat-card-subtitle>Asociaciones activas</mat-card-subtitle>
          </mat-card-header>
          <mat-card-content>
            <div class="stat-value">{{ serviceCount }}</div>
          </mat-card-content>
          <mat-card-actions>
            <a mat-button color="primary" routerLink="/services">Ver todos</a>
          </mat-card-actions>
        </mat-card>
      </div>
    </div>
  `,
  styles: [`
    .card-icon {
      font-size: 40px;
      width: 40px;
      height: 40px;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 8px;
      color: white;

      &.municipalities { background: #3f51b5; }
      &.users { background: #4caf50; }
      &.services { background: #ff9800; }
    }

    .stat-value {
      font-size: 3rem;
      font-weight: 300;
      margin: 16px 0 8px;
    }
  `],
})
export class DashboardComponent implements OnInit {
  private municipalityService = inject(MunicipalityService);
  private userService = inject(UserService);
  private serviceService = inject(ServiceService);

  municipalityCount = 0;
  userCount = 0;
  serviceCount = 0;

  ngOnInit(): void {
    forkJoin({
      municipalities: this.municipalityService.getAll(),
      users: this.userService.getAll(),
      services: this.serviceService.getAll(),
    }).subscribe({
      next: (data) => {
        this.municipalityCount = data.municipalities.length;
        this.userCount = data.users.length;
        this.serviceCount = data.services.length;
      },
      error: (err) => console.error('Error loading dashboard data', err),
    });
  }
}
