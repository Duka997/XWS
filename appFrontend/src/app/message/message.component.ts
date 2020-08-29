import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Poruka} from '../model/poruka';
import {PorukaService} from '../services/poruka.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  po =  new Poruka(null,null,null,null);

  constructor(private router: Router,  private _porukaServis: PorukaService) { }

  ngOnInit(): void {
  }
  
  onSubmit(){
    var userId = parseInt(localStorage.getItem('id'));
    this.po.posiljalacId=userId;
    console.log(this.po);
    this.po.primalacId = parseInt(localStorage.getItem('primalac'))
      this._porukaServis.posaljiPoruku(this.po)
     .subscribe(
         data=>{
          console.log('Success!', JSON.stringify(data))
          alert('Poruka poslata');
  
         } ,
          error=> console.error('Error!',error)
      )
      localStorage.removeItem('primalac');
  }
}
