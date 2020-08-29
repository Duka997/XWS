import { Component, OnInit } from '@angular/core';
import { CommentService } from 'src/app/services/comment.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-comment-admin',
  templateUrl: './comment-admin.component.html',
  styleUrls: ['./comment-admin.component.css']
})
export class CommentAdminComponent implements OnInit {
  komentari = [];

  constructor(private commentService: CommentService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.commentService.getKomentare().subscribe(data => {
      this.komentari = data;
      console.log("Svi komentari: ",this.komentari);
    }, error => {
      this.toastr.error('There was an error while getting comments');
    });
  }

  refresh(){
    this.commentService.getKomentare().subscribe(data => {
      this.komentari = data;
      console.log("Svi komentari: ",this.komentari);
    }, error => {
      this.toastr.error('There was an error while getting comments');
    });
  }

  odobri(komentar: any){
    this.commentService.prihvatiIliOdbij(komentar.id, false).subscribe(
      data => {
          this.toastr.success("Comment " + komentar.tekst +" refused", "Comment")
          this.refresh();
      },
      err => {
        this.toastr.error("Error refusing comment", "Comment")
      });
  }

  odbij(komentar: any){
    this.commentService.prihvatiIliOdbij(komentar.id, true).subscribe(
      data => {
          this.toastr.success("Comment " + komentar.tekst +" accepted", "Comment")
          this.refresh();
      },
      err => {
        this.toastr.error("Error accepting comment", "Comment")
      });
  }

}
