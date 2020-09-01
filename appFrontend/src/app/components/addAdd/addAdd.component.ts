import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Oglas } from 'src/app/model/add';
import { Vozilo } from 'src/app/model/car';
import { Cjenovnik } from 'src/app/model/priceList';
import { DodajVoziloService } from '../addCar/addCar.service';
import { DodajOglasService } from './addAdd.service';
import { CjenovnikService } from '../pricelist/pricelist.service';
import { USER_ID_KEY } from 'src/app/config/local-storage-keys';
import { CarService } from 'src/app/services/car.service';




@Component({
  selector: 'app-addOglas',
  templateUrl: './addAdd.component.html'
})
export class DodajOglasComponent implements OnInit {

 oglas =  new Oglas(null,null,null, null,null,null, null,null);
 public vozila : Vozilo[];
 userId : number;
 cjenovnici: Cjenovnik[];


  constructor(private _voziloServis : DodajVoziloService, private router: Router,
   private _oglasService : DodajOglasService, private cjenovnikService: CjenovnikService, private carService: CarService) { }

  ngOnInit(): void {

    this.oglas.userId = parseInt(localStorage.getItem("id"));

    this.carService.getCarsByUser(localStorage.getItem('id')).subscribe(
      data => {
        this.vozila = data;
        console.log(data);
      },
      error=> console.error('Error!', error)
    )
    
    this.cjenovnikService.getCjenovnik(localStorage.getItem("id")).subscribe(
      data => {
        this.cjenovnici = data;
      },
      error=> console.error('Error!', error)
    )

}
  onSubmit(){ 
   
      console.log(this.oglas);
        this._oglasService.dodajOglas(this.oglas)
       .subscribe(
           data=>{
            console.log('Success!', JSON.stringify(data))
            alert('New ad has been added!');
            this.router.navigate(['homepageAgent']);
           } ,
            error=> console.error('Error!',error)
        )
    }


}