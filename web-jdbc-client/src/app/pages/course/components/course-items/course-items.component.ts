import { Component, OnInit } from '@angular/core';
import {CourseResponseDto} from "../../../../model/course-response-dto";
import {CourseApiService} from "../../../../service/course-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DataTableResponseDto} from "../../../../model/data-table-response-dto";

@Component({
  selector: 'app-course-items',
  templateUrl: './course-items.component.html',
  styleUrls: ['./course-items.component.scss']
})
export class CourseItemsComponent implements OnInit {

  courses: CourseResponseDto[] | undefined;
  datatable: DataTableResponseDto | undefined;

  constructor(private _courseApiService: CourseApiService,
              private _router: Router,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this._loadAll();
    this._loadAllByParams(this.datatable?.page,this.datatable?.size, this.datatable?.sort, this.datatable?.order);
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

  private _loadAllByParams(page: number | undefined, size: number | undefined, sort: string | undefined, order:string | undefined): void {
    this._courseApiService.loadAllByParams(page,size,sort,order).subscribe(courses => {
      this.courses = courses;
    })
  }
}
