import { HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  private apiUrl = "http://localhost:8080/api/weather";

  constructor(private http: HttpClient) { }

  getForecast(city : string) {
    return this.http.get<any>(`${this.apiUrl}/${city}`);
  }
}
