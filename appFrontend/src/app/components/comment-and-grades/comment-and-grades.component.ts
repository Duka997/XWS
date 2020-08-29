import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/services/car.service';
import { CommentService } from 'src/app/services/comment.service';
import { GradeService } from 'src/app/services/grade.service';

@Component({
  selector: 'app-comment-and-grades',
  templateUrl: './comment-and-grades.component.html',
  styleUrls: ['./comment-and-grades.component.css']
})
export class CommentAndGradesComponent implements OnInit {

  public vozila: [];
  public vozilo: {
    id: null
  } = null;
  public komentar: string;
  public komentari: [];
  public ocene: [];
  mode: string = 'VIEW';

  constructor(private carService: CarService, private commentService: CommentService, private gradeService: GradeService) { }

  ngOnInit(): void {
    this.carService.getVozila(localStorage.getItem('username')).subscribe(
      data => {this.vozila = data}
    );
  }

  refresh(){
    this.carService.getVozila(localStorage.getItem('username')).subscribe(
      data => {this.vozila = data}
    );
  }

  voziloPromjena(vozilo: any){
    this.commentService.getKomentari(vozilo.id).subscribe(
      data=> {
        this.komentari =  data;
      }
    );

    this.gradeService.getOcjene(vozilo.id).subscribe(
      data=> {
        this.ocene =  data;
      }
    );
  }

  onClickDodaj(){
    if(this.mode == 'VIEW'){
      this.mode = 'ADD';
      return;
    }
    
    let newKomenatar  = {
      id: null,
      tekst: this.komentar,
      vozilo: {
        id: this.vozilo.id,
      }
    }

    

    this.commentService.dodajKomentar(newKomenatar, localStorage.getItem('username')).subscribe(
      data=>{
        this.refresh();      
      }
    );

    this.vozilo = null;
    this.komentar = '';
    this.mode = 'VIEW';
    this.komentari = [];
    this.ocene = [];
  }

}

