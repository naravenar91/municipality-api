import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Municipality } from '../models';

@Injectable({ providedIn: 'root' })
export class MunicipalityService {
  private http = inject(HttpClient);
  private readonly url = '/api/municipality';

  getAll(): Observable<Municipality[]> {
    return this.http.get<Municipality[]>(this.url);
  }

  getById(id: number): Observable<Municipality> {
    return this.http.get<Municipality>(`${this.url}/${id}`);
  }

  create(municipality: Municipality): Observable<Municipality> {
    return this.http.post<Municipality>(this.url, municipality);
  }

  update(municipality: Municipality): Observable<Municipality> {
    return this.http.put<Municipality>(this.url, municipality);
  }
}
