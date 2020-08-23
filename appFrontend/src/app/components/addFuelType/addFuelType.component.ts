import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipGoriva } from 'src/app/model/fuelType';
import { TipGorivaService } from './addFuelType.service';


@Component({
  selector: 'app-tipGoriva',
  templateUrl: './addFuelType.component.html'
})
export class AddTipGoriva implements OnInit {

 gorivo =  new TipGoriva(null,null);
  constructor(private _tipGorivaServis : TipGorivaService, private router : Router) { }

  ngOnInit(): void {

  }
  onSubmit(){ 
    console.log(this.gorivo);
      this._tipGorivaServis.dodajTipGoriva(this.gorivo)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          alert('New fuel type has been added!');
          this.router.navigate(['homepageAdmin']);
         } ,
          error=> console.error('Error!',error)
      )
  }

}