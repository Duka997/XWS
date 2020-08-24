import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { DodajMarkuAutomobilaService } from './addMarkuAutomobila.service';
import { MarkaAutomobila } from 'src/app/model/markaAutomobila';


@Component({
  selector: 'app-addMarkuAutomobila',
  templateUrl: './addMarkuAutomobila.component.html'
})
export class AddMarkaAutomobila implements OnInit {

 marka =  new MarkaAutomobila(null,null,null);
  constructor(private _markaServis : DodajMarkuAutomobilaService, private router : Router) { }

  ngOnInit(): void {

  }
  onSubmit(){ 
    console.log(this.marka);
      this._markaServis.dodajMarku(this.marka)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          alert('New car mark has been added!');
          this.router.navigate(['homepageAdmin']);
         } ,
          error=> console.error('Error!',error)
      )
  }

}