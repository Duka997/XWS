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
  constructor(private router: Router,
              private toastr: ToastrService) { }

  ngOnInit(): void {
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
}
