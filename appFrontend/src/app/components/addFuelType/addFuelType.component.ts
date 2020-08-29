import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TipGoriva } from 'src/app/model/fuelType';
import { TipGorivaService } from './addFuelType.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-tipGoriva',
  templateUrl: './addFuelType.component.html'
})
export class AddTipGoriva implements OnInit {

 gorivo =  new TipGoriva(null,null);
  constructor(private toastr: ToastrService, private _tipGorivaServis : TipGorivaService, private router : Router) { }

  ngOnInit(): void {

  }
  onSubmit(){ 
    console.log(this.gorivo);
      this._tipGorivaServis.dodajTipGoriva(this.gorivo)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          this.toastr.success('New fuel type has been added!');
          this.router.navigate(['homepageAdmin/carEntities']);
         } ,
          error=> console.error('Error!',error)
      )
  }

}