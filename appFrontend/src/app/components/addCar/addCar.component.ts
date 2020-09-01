import { Component, OnInit} from '@angular/core';

import { Router } from '@angular/router';

import { error } from '@angular/compiler/src/util';
import { Vozilo } from 'src/app/model/car';
import { MarkaAutomobila } from 'src/app/model/markaAutomobila';
import { KlasaAutomobila } from 'src/app/model/carClass';
import { TipGoriva } from 'src/app/model/fuelType';
import { TipMjenjaca } from 'src/app/model/gearboxType';
import { Slika } from 'src/app/model/image';
import { DodajKlasuAutomobilaService } from '../addCarClass/addCarClass.service';
import { DodajMarkuAutomobilaService } from '../addMarkuAutomobila/addMarkuAutomobila.service';
import { TipGorivaService } from '../addFuelType/addFuelType.service';
import { DodajVoziloService } from './addCar.service';
import { TipMjenjacaService } from '../addGearboxType/addGearboxType.service';


@Component({
  selector: 'app-addCar',
  templateUrl: './addCar.component.html'
})
export class AddCarComponent implements OnInit {

 vozilo =  new Vozilo(null,null,null, null,null,null, null,null,null, false,false,null,null,null);
 public marke : MarkaAutomobila[];
 public klase : KlasaAutomobila[];
 public gorivo : TipGoriva[];
 public mjenjac : TipMjenjaca[];
 imageFiles = [];
 imageURLs: Slika[] = [];

  constructor(private _klasaServis : DodajKlasuAutomobilaService, 
    private _markaServis : DodajMarkuAutomobilaService, private _voziloServis : DodajVoziloService, private router: Router,
    private _gorivoServis : TipGorivaService, private _mjenjacServis : TipMjenjacaService) { }

  ngOnInit(): void {
    this._klasaServis.getKlase().subscribe(
      data=>{ 
        console.log(data+'klase');
          this.klase = data;
      },
      error=> console.error('Error!', error)
  )
  this._markaServis.getMarke().subscribe(
    data=>{ 
      console.log(data);
        this.marke = data;
    },
    error=> console.error('Error!', error)
)
this._gorivoServis.getTipoveGoriva().subscribe(
  data => {
    this.gorivo = data;
  },
  error=> console.error('Error!', error)
)
this._mjenjacServis.getTipoveMjenjaca().subscribe(
  data => {
    this.mjenjac = data;
  },
  error=> console.error('Error!', error)
)
}
  onSubmit(){ 
      this.vozilo.slike = this.imageURLs;
      console.log(this.vozilo);
      var agentId = parseInt(localStorage.getItem('id'));
      this._voziloServis.dodajVozilo(this.vozilo, agentId)
       .subscribe(
           data=>{
            console.log('Success!', JSON.stringify(data))
            alert('New car has been added!');
            this.router.navigate(['homepageAgent']);
           } ,
            error=> console.error('Error!',error)
        )
    }

    fileUpload(event) {
      if (event.target.files && event.target.files[0]) {
        var filesAmount = event.target.files.length;
        for (let i = 0; i < filesAmount; i++) {
          var reader = new FileReader();
  
          this.imageFiles.push(event.target.files[i]);
          reader.onload = (event: any) => {
            console.log(event.target.result)
            this.imageURLs.push(event.target.result);
          }
  
          reader.readAsDataURL(event.target.files[i]);
        }
      }
    }

}