import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MarkaAutomobila } from 'src/app/model/markaAutomobila';
import { TipMjenjaca } from 'src/app/model/gearboxType';
import { TipGoriva } from 'src/app/model/fuelType';
import { KlasaAutomobila } from 'src/app/model/carClass';
import { DodajMarkuAutomobilaService } from 'src/app/components/addMarkuAutomobila/addMarkuAutomobila.service';
import { TipMjenjacaService } from 'src/app/components/addGearboxType/addGearboxType.service';
import { TipGorivaService } from 'src/app/components/addFuelType/addFuelType.service';
import { DodajKlasuAutomobilaService } from 'src/app/components/addCarClass/addCarClass.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-homepage-admin',
  templateUrl: './homepage-admin.component.html',
  styleUrls: ['./homepage-admin.component.css']
})
export class HomepageAdminComponent implements OnInit {

  editModel = new MarkaAutomobila(null,null,null);
  editModelKlasa = new KlasaAutomobila(null, null);
  editModelTipGoriva = new TipGoriva(null, null);
  editModelTipMenjaca = new TipMjenjaca(null, null);
  edit : boolean = false;
  edit1 : boolean = false;
  edit2 : boolean = false;
  edit3 : boolean = false;
  categories = [];
  komentari = [];
  marke = [];
  marka = new MarkaAutomobila(null,null,null);
  public editedMarka:MarkaAutomobila;
  public editedKlasa:KlasaAutomobila;
  public editedTipGoriva:TipGoriva;
  public editedTipMenjaca:TipMjenjaca;
  goriva = [];
  klase = [];
  menjaci = [];
  categoryName: string = '';

  constructor( private dodajMarkuAutomobilaService: DodajMarkuAutomobilaService,private router: Router,
    private tipMenjacaService : TipMjenjacaService, private toastr: ToastrService, private tipGorivaService : TipGorivaService,private dodajKlasuAutomobilaService : DodajKlasuAutomobilaService) { }

  ngOnInit(): void {
    this.getAllMarkaAutomobila();
    this.getAllKlase();
    this.getAllTipGoriva();
    this.getAllTipMenjaca();
  }

  logout(){
    localStorage.removeItem("user-token");
    localStorage.removeItem("expires-in");
    localStorage.removeItem("username");
    localStorage.removeItem("user-role");
    localStorage.removeItem("refresh-token");
    localStorage.removeItem("id");
    this.router.navigate(['homepage']);
  }

  private getAllMarkaAutomobila(): void{
    this.dodajMarkuAutomobilaService.getMarke().subscribe(data => {
      this.marke = data;
    },error => {
      this.toastr.error('Error while getting car marks');
    });
  }

  private getAllKlase(): void{
    this.dodajKlasuAutomobilaService.getKlase().subscribe(data => {
      this.klase = data;
    },error => {
      this.toastr.error('Error while getting car classes');
    });
  }

  private getAllTipGoriva(): void{
    this.tipGorivaService.getTipoveGoriva().subscribe(data => {
      this.goriva = data;
    },error => {
      this.toastr.error('Error while getting fuel types');
    });
  }

  private getAllTipMenjaca(): void{
    this.tipMenjacaService.getTipoveMjenjaca().subscribe(data => {
      this.menjaci = data;
    },error => {
      this.toastr.error('Error while getting gearbox types');
    });
  }

  onSubmit1(){
    this.dodajMarkuAutomobilaService.editMarku(this.editModel).subscribe(
        data=>{
            alert('Mark edited!');
            this.editedMarka = data as MarkaAutomobila;
            this.getAllMarkaAutomobila();
            this.router.navigate(['/homepageAdmin']);
        },
        error=> console.error('Error!', error)
    )
    this.edit = false; 
  }

  onSubmit(){
    this.dodajKlasuAutomobilaService.editKlasu(this.editModelKlasa).subscribe(
        data=>{
            alert('Class edited!');
            this.editedKlasa = data as KlasaAutomobila;
            this.getAllKlase();
            this.router.navigate(['/homepageAdmin']);
        },
        error=> console.error('Error!', error)
    )
    this.edit1 = false; 
  }

  onSubmit2(){
    this.tipGorivaService.editTipGoriva(this.editModelTipGoriva).subscribe(
        data=>{
            alert('Fuel type edited!');
            this.editedTipGoriva = data as TipGoriva;
            this.getAllTipGoriva();
            this.router.navigate(['/homepageAdmin']);
        },
        error=> console.error('Error!', error)
    )
    this.edit2 = false; 
  }

  onSubmit3(){
    this.tipMenjacaService.editTipMenjaca(this.editModelTipMenjaca).subscribe(
        data=>{
            alert('Gearbox type edited!');
            this.editedTipMenjaca = data as TipMjenjaca;
            this.getAllTipMenjaca();
            this.router.navigate(['/homepageAdmin']);
        },
        error=> console.error('Error!', error)
    )
    this.edit3 = false; 
  }

    editMarku(marka: MarkaAutomobila):void{   
      this.edit = true;
      this.editModel.nazivMarke = marka.nazivMarke;
      this.editModel.model = marka.model;
      this.editModel.id = marka.id;
      
  }

editKlasu(klasa: KlasaAutomobila):void{   
  this.edit1 = true;
  this.editModelKlasa.naziv = klasa.naziv;
  this.editModelKlasa.id = klasa.id;
  
}

deleteKlasu(id):void{
  this.dodajKlasuAutomobilaService.deleteKlasu(id).subscribe(
    data=>{
      alert('Car class deleted!');
      this.getAllKlase();
      
  },
  error=> console.error('Error!', error)
  )
}

deleteMarku(id):void{
  this.dodajMarkuAutomobilaService.deleteMarku(id).subscribe(
    data=>{
      alert('Car mark deleted!');
      this.getAllMarkaAutomobila();
      
  },
  error=> console.error('Error!', error)
  )
}

deleteTipMenjaca(id):void{
  this.tipMenjacaService.deleteTipMenjaca(id).subscribe(
    data=>{
      alert('Gearbox type deleted!');
      this.getAllTipMenjaca();
  },
  error=> console.error('Error!', error)
  )
}

deleteTipGoriva(id):void{
  this.tipGorivaService.deleteTiGoriva(id).subscribe(
    data=>{
      alert('Fuel type deleted!');
      this.getAllTipGoriva();
  },
  error=> console.error('Error!', error)
  )
}

editTipGoriva(gorivo: TipGoriva):void{   
  this.edit2 = true;
  this.editModelTipGoriva.naziv = gorivo.naziv;
  this.editModelTipGoriva.id = gorivo.id;
  
}

editTipMenjaca(menjac: TipMjenjaca):void{   
  this.edit3 = true;
  this.editModelTipMenjaca.naziv = menjac.naziv;
  this.editModelTipMenjaca.id = menjac.id;
  
}


}
