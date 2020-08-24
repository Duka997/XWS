import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KlasaAutomobila } from 'src/app/model/carClass';
import { DodajKlasuAutomobilaService } from './addCarClass.service';


@Component({
  selector: 'app-dodajKlasu',
  templateUrl: './addCarClass.component.html'
})
export class DodajKlasuAutomobila implements OnInit {

 klasa =  new KlasaAutomobila(null,null);
 constructor(private _klasaServis : DodajKlasuAutomobilaService, private router : Router) { }

  ngOnInit(): void {

  }
  onSubmit(){ 
    console.log(this.klasa);
      this._klasaServis.dodajKlasu(this.klasa)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          alert('New car class has been added!');
          this.router.navigate(['homepageAdmin']);
         } ,
          error=> console.error('Error!',error)
      )
  }

}