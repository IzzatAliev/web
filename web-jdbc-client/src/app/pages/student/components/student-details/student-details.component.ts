import { Component, OnInit } from '@angular/core';
import {StudentResponseDto} from "../../../../model/student-response-dto";
import {appConst} from "../../../../app.const";
import {ActivatedRoute, Router} from "@angular/router";
import {StudentApiService} from "../../../../service/student-api.service";

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.scss']
})
export class StudentDetailsComponent implements OnInit {

  private _id: number | undefined;
  student: StudentResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(private _route: ActivatedRoute, private _router: Router, private _studentApiService: StudentApiService) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._studentApiService.loadById(this._id).subscribe(student => {
      this.student = student;
    });
  }

  showAllCourses() {
    this._router.navigateByUrl('courses?studentId=' + this.student?.id);
  }

}
