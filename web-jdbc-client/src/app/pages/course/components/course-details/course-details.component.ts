import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CourseApiService} from "../../../../service/course-api.service";
import {CourseResponseDto} from "../../../../model/course-response-dto";
import {appConst} from "../../../../app.const";

@Component({
  selector: 'app-course-details',
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})
export class CourseDetailsComponent implements OnInit {

  private _id: number | undefined;
  course: CourseResponseDto | undefined;
  dateFormat = appConst.dateFormat;

  constructor(private _route: ActivatedRoute, private _router: Router, private _courseApiService: CourseApiService) { }

  ngOnInit(): void {
    this._id = Number(this._route.snapshot.paramMap.get('id'));
    this._courseApiService.loadById(this._id).subscribe(course => {
      this.course = course;
    });
  }

  showAllStudents() {
    this._router.navigateByUrl('students?courseId=' + this.course?.id);
  }

  addNewStudents() {
    this._router.navigateByUrl('students/new?courseId=' + this.course?.id);
  }

}
