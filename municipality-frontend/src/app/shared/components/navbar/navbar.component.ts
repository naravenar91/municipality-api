import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive, MatToolbarModule, MatButtonModule, MatIconModule],
  template: `
    <mat-toolbar color="primary" class="navbar">
      <mat-icon class="logo-icon">account_balance</mat-icon>
      <span class="brand" routerLink="/">Municipality Admin</span>

      <nav class="nav-links">
        <a mat-button routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}">
          <mat-icon>dashboard</mat-icon>
          Dashboard
        </a>
        <a mat-button routerLink="/municipalities" routerLinkActive="active">
          <mat-icon>location_city</mat-icon>
          Municipalidades
        </a>
        <a mat-button routerLink="/users" routerLinkActive="active">
          <mat-icon>people</mat-icon>
          Usuarios
        </a>
        <a mat-button routerLink="/services" routerLinkActive="active">
          <mat-icon>miscellaneous_services</mat-icon>
          Servicios
        </a>
      </nav>
    </mat-toolbar>
  `,
  styles: [`
    .navbar {
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      z-index: 1000;
      display: flex;
      align-items: center;
      gap: 8px;
    }

    .logo-icon {
      margin-right: 8px;
    }

    .brand {
      font-size: 1.1rem;
      cursor: pointer;
      margin-right: 24px;
    }

    .nav-links {
      display: flex;
      gap: 4px;

      a {
        color: white;
        opacity: 0.85;
        transition: opacity 0.2s;

        &:hover, &.active {
          opacity: 1;
        }

        mat-icon {
          margin-right: 4px;
          font-size: 20px;
          height: 20px;
          width: 20px;
        }
      }
    }
  `],
})
export class NavbarComponent {}
