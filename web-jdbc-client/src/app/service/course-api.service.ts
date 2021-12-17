import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {CourseResponseDto} from "../model/course-response-dto";
import {CourseRequestDto} from "../model/course-request-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CourseApiService {

  private _apiUrl = environment.apiUrl + appConst.coursesPath;

  constructor(private _apiService: ApiService<CourseRequestDto, CourseResponseDto>) { }

  loadAll(): Observable<CourseResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadAllByParams(): Observable<CourseResponseDto[]> {
    const params: HttpParams = new HttpParams();
    params.set('page',"")
    params.set('sort',"")
    params.set('order',"")
    return this._apiService.loadAllByParams(this._apiUrl, params);
  }

  loadById(id: number): Observable<CourseResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  create(dto: CourseRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: CourseRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }
}
