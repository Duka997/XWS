import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Vozilo } from 'src/app/model/car';
import { Occupied } from 'src/app/model/occupied';
import { OccupationService } from 'src/app/services/occupation.service';
import { CarService } from 'src/app/services/car.service';

@Component({
  selector: 'app-occupation',
  templateUrl: './occupation.component.html',
  styleUrls: ['./occupation.component.css']
})
export class OccupationComponent implements OnInit {
  newOccupationForm;
  cars : Vozilo[];
  occupations: Occupied[];
  selectedOccupation: Occupied;

  constructor(private occupationService: OccupationService,
              private carService: CarService,
              private _toastr: ToastrService) {
    this.selectedOccupation = {
      od: null,
      doo: null,
      voziloId: -1,
      id: null,
      vozilo: null,
      oglasId: null
    };
   }


  ngOnInit(): void {
    var userId = parseInt(localStorage.getItem('id'));
    this.occupationService.getOccupations(userId).subscribe(
      data =>{
        this.occupations = data;
        console.log("Occupations: ", this.occupations);
      },
      error =>{
        this._toastr.error('Error getting occupations', 'Occupations');
      }
    );
    this.getData();
  }

  refresh(){
    var userId = parseInt(localStorage.getItem('id'));
    this.occupationService.getOccupations(userId).subscribe(
      data =>{
        this.occupations = data;
      },
      error =>{
        this._toastr.error('Error getting occupations', 'Occupations');
      });
  }

  create() {
    if(this.selectedOccupation.od == undefined || this.selectedOccupation.doo == undefined || this.selectedOccupation.doo == null ||this.selectedOccupation.od == null || this.selectedOccupation.voziloId == -1){
      this._toastr.info('Please fill date to, date from and car','Occupation');
      return;
    }

    console.log("occ: ", this.selectedOccupation);
    this.occupationService.setOccupied(this.selectedOccupation).subscribe(
      data=>{
        this._toastr.success("Occupation succesfully created", "Occupation");
        this.selectedOccupation.voziloId = -1;
        this.selectedOccupation.od = null;
        this.selectedOccupation.doo = null;
        this.refresh();
      },
      error =>{
        if(error.status == 400){
          this._toastr.info('Dates are not valid','Occupation');
        }else if(error.status == 409) {
          this._toastr.warning('Car is already rented for that period', 'Occupation');
        }else{
          this._toastr.error("Error setting occupation", "Occupation");
        }
      }
    );
    
  }

  getData(){
    var userId = parseInt(localStorage.getItem('id'));
    this.carService.getCarsByUser(userId).subscribe(
      (data: Vozilo[]) => { 
        this.cars = data;
        console.log("Cars: ", this.cars)
      }, error => this._toastr.error("Error getting cars", "Cars"));
  }
  

}
