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
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
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
