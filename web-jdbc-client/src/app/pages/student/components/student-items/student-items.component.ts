import { Component, OnInit } from '@angular/core';
import {StudentResponseDto} from "../../../../model/student-response-dto";
import {StudentApiService} from "../../../../service/student-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-student-items',
  templateUrl: './student-items.component.html',
  styleUrls: ['./student-items.component.scss']
})
export class StudentItemsComponent implements OnInit {

  students: StudentResponseDto[] | undefined;

  constructor(private _studentApiService: StudentApiService,
              private _router: Router,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this._loadAll();
  }

  loadById(id: number): void {
    this._router.navigate([id], { relativeTo: this._route });
  }

  deleteById(id: number): void {
    this._studentApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addStudent(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  private _loadAll(): void {
    this._studentApiService.loadAll().subscribe(students => {
      this.students = students;
    });
  }

}
