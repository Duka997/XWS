import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { TipMjenjaca } from 'src/app/model/gearboxType';
import { TipMjenjacaService } from './addGearboxType.service';



@Component({
  selector: 'app-tipMjenjaca',
  templateUrl: './addGearboxType.component.html'
})
export class DodajTipMjenjaca implements OnInit {

 tip =  new TipMjenjaca(null,null);
  constructor(private _tipMjenjcaServis : TipMjenjacaService, private router : Router) { }

  ngOnInit(): void {
  }

  
  onSubmit(){ 
      this._tipMjenjcaServis.dodajTipMjenjaca(this.tip)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          alert('New gearbox type has been added!');
          this.router.navigate(['homepageAdmin']);
         } ,
          error=> console.error('Error!',error)
      )
  }

}