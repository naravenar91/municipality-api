import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ServiceAssociation } from '../models';

@Injectable({ providedIn: 'root' })
export class ServiceService {
  private http = inject(HttpClient);
  private readonly url = '/api/service';

  getAll(): Observable<ServiceAssociation[]> {
    return this.http.get<ServiceAssociation[]>(this.url);
  }

  create(association: ServiceAssociation): Observable<ServiceAssociation> {
    return this.http.post<ServiceAssociation>(this.url, association);
  }
}
