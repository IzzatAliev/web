import { Component, OnInit } from '@angular/core';
import {CourseResponseDto} from "../../../../model/course-response-dto";
import {CourseApiService} from "../../../../service/course-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-course-items',
  templateUrl: './course-items.component.html',
  styleUrls: ['./course-items.component.scss']
})
export class CourseItemsComponent implements OnInit {

  courses: CourseResponseDto[] | undefined;

  constructor(private _courseApiService: CourseApiService,
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
    this._courseApiService.deleteById(id).subscribe(() => {
      window.location.reload();
    });
  }

  addCourse(): void {
    this._router.navigate(['new'], { relativeTo: this._route });
  }

  private _loadAll(): void {
    this._courseApiService.loadAll().subscribe(courses => {
      this.courses = courses;
    });
  }
}
