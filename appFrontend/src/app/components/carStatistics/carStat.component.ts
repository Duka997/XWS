import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/services/car.service';
import { ToastrService } from 'ngx-toastr';
import { Vozilo } from 'src/app/model/car';
import { IStatistika } from './model/statistika';

@Component({
    selector: 'app-carStat',
    templateUrl: './carStat.component.html'
})

export class CarStatComponent implements OnInit {

    allCars: IStatistika;

    constructor(private carService: CarService,
        private _toastr: ToastrService) { }

    ngOnInit(): void {
        var ownersID = parseInt(localStorage.getItem('id'));
        this.carService.getCarStatistics(ownersID).subscribe(
            (data: IStatistika)=>{
              this.allCars = data;
              console.log("Sva vozila: ", this.allCars);
            }
        )
    }
  

}