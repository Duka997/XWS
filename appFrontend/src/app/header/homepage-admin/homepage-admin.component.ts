import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage-admin',
  templateUrl: './homepage-admin.component.html',
  styleUrls: ['./homepage-admin.component.css']
})
export class HomepageAdminComponent implements OnInit {

  constructor(private router: Router) { }

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