import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Oglas } from 'src/app/model/add';
import { Vozilo } from 'src/app/model/car';
import { Cjenovnik } from 'src/app/model/priceList';
import { DodajVoziloService } from '../addCar/addCar.service';
import { DodajOglasService } from './addAdd.service';
import { CjenovnikService } from '../pricelist/pricelist.service';
import { USER_ID_KEY } from 'src/app/config/local-storage-keys';




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
   private _oglasService : DodajOglasService, private cjenovnikService: CjenovnikService) { }

  ngOnInit(): void {

    this.oglas.userId = parseInt(localStorage.getItem(USER_ID_KEY));

    this._oglasService.getVozila().subscribe(
      data => {
        this.vozila = data;
        console.log(data);
      },
      error=> console.error('Error!', error)
    )
    
    this.cjenovnikService.getCjenovnik(localStorage.getItem("user-username-key")).subscribe(
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
            alert('New add has been added!');
            this.router.navigate(['homepageAdmin']);
           } ,
            error=> console.error('Error!',error)
        )
    }


}