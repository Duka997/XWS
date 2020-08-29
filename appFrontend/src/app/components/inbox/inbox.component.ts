import { Component, OnInit } from '@angular/core';
import {Poruka} from '../../model/poruka';
import { Router } from '@angular/router';
import {PorukaService} from '../../services/poruka.service';

@Component({
    selector: 'app-inbox',
    templateUrl: './inbox.component.html'
  })
  export class Inbox implements OnInit {
  
    public poruke : Poruka[];
    po =  new Poruka(null,null,null,null);

    
  
    constructor(private router: Router,  private _porukaServis: PorukaService) { }
  
    ngOnInit(): void {
        var userId = parseInt(localStorage.getItem('id'));
        this._porukaServis.getMojePoruke(userId).subscribe(
            data=>{ 
                this.poruke = data;
            },
            error=> console.error('Error!', error)
        )
      }
  
      odgovori(posiljalacId:any){
        var userId = parseInt(localStorage.getItem('id'));
        this.po.posiljalacId = userId;
        this.po.primalacId = posiljalacId;
        localStorage.setItem("primalac", posiljalacId);
        this.router.navigate(['homepageAgent/inbox/reply']);
      }
  
  }