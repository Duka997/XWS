import { Component, OnInit } from '@angular/core';
import { CarService } from 'src/app/services/car.service';
import { CommentService } from 'src/app/services/comment.service';
import { GradeService } from 'src/app/services/grade.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-comment-and-grades',
  templateUrl: './comment-and-grades.component.html',
  styleUrls: ['./comment-and-grades.component.css']
})
export class CommentAndGradesComponent implements OnInit {

  public vozila: [];
  public selektovanoVozilo: {
    id: null
  };
  public role: string;
  public komentarTekst: string ='';
  public komentar:{};
  public selektovaniKomentar:{
    oglasId: '',
    voziloId: '',
    userUsername: '',
    tekst: ''
  }; 
  public komentari: [];
  public ocene: [];
  mode: string = 'VIEW';
  username: string;
  checkCommentFlag: boolean = false;

  constructor(private carService: CarService,
            private commentService: CommentService, 
            private gradeService: GradeService,
            private toastr: ToastrService) { }

  ngOnInit(): void {
    this.role = localStorage.getItem("user-role");
    this.username = localStorage.getItem("username");
    this.carService.getVozila(localStorage.getItem('username')).subscribe(
      data => {this.vozila = data}
    );
  }

  cancel(){
    this.mode='VIEW';
    this.komentarTekst = '';
  }

  addComment(comment){
    this.mode = 'COMMENT';
    this.selektovaniKomentar = comment;
  }

  refresh(){
    this.carService.getVozila(localStorage.getItem('username')).subscribe(
      data => {this.vozila = data}
    );
  }

  voziloPromjena(){
    this.mode = 'VIEW';
    this.commentService.getKomentari(this.selektovanoVozilo.id).subscribe(
      data=> {
        this.komentari =  data;
      }
    );

    this.gradeService.getOcjene(this.selektovanoVozilo.id).subscribe(
      data=> {
        this.ocene =  data;
      }
    );
  }

  onClickDodaj(){
    if(this.komentar == ''){
      this.toastr.info('Please, insert text', "Comment");
      return;
    }
    
    let newKomenatar  = {
      id: null,
      odobren:false,
      role: localStorage.getItem("user-role"),
      userUsername: localStorage.getItem("username"),
      tekst: this.komentarTekst,
      oglasId: this.selektovaniKomentar.oglasId ,
      userId: localStorage.getItem("id"),
      voziloId: this.selektovaniKomentar.voziloId
    }

    this.commentService.dodajKomentarOdgovor(newKomenatar).subscribe(
      data=>{
        this.toastr.success("Comment succesfully created", "Comment");    
      },
      error =>{
        if(error.status == 400)
          this.toastr.info("Comment already sent", "Comment");
        else
          this.toastr.error("Error creating comment", "Comment");
      }
    );

    this.komentarTekst = '';
    this.mode ='VIEW';
  }

}

