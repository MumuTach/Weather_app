import { Component } from '@angular/core';
import { HomeService } from '../../services/home.service';


@Component({
  selector: 'app-home',
  standalone: false,
  
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  city : string = '';
  weatherData : any;
  forecastData: any[] = [];

  constructor(private homeService : HomeService) {
    console.log('HomeComponent chargé !');
  }

  getWeather() {
    if (this.city) {
      this.homeService.getForecast(this.city).subscribe((data) => {
        this.weatherData = {
          name: data.city.name,
          main: data.list[0].main,
          weather: data.list[0].weather
        };

        const groupedForecast: any = {};
        data.list.forEach((item: any) => {
          const date = item.dt_txt.split(" ")[0]; // Extraire la date (YYYY-MM-DD)
          if (!groupedForecast[date] && item.dt_txt.includes("12:00:00")) {
            groupedForecast[date] = item;
          }
        });

        this.forecastData = Object.values(groupedForecast);
      }, error => {
        console.error('Error fetching weather data', error);
      });
    }
  }

  goBack(){
    this.weatherData = null; 
    this.city = '';
  }
}
