import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { TipMjenjaca } from 'src/app/model/gearboxType';
import { TipMjenjacaService } from './addGearboxType.service';
import { ToastrService } from 'ngx-toastr';



@Component({
  selector: 'app-tipMjenjaca',
  templateUrl: './addGearboxType.component.html'
})
export class DodajTipMjenjaca implements OnInit {

 tip =  new TipMjenjaca(null,null);
  constructor(private toastr: ToastrService, private _tipMjenjcaServis : TipMjenjacaService, private router : Router) { }

  ngOnInit(): void {
  }

  
  onSubmit(){ 
      this._tipMjenjcaServis.dodajTipMjenjaca(this.tip)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          this.toastr.success('New gearbox type has been added!');
          this.router.navigate(['homepageAdmin/carEntities']);
         } ,
          error=> console.error('Error!',error)
      )
  }

}