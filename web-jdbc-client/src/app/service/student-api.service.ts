import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {appConst} from "../app.const";
import {ApiService} from "./api.service";
import {StudentResponseDto} from "../model/student-response-dto";
import {StudentRequestDto} from "../model/student-request-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StudentApiService {

  private _apiUrl = environment.apiUrl + appConst.studentsPath;

  constructor(private _apiService: ApiService<StudentRequestDto, StudentResponseDto>) { }

  loadAll(): Observable<StudentResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadAllByCourse(courseId: string): Observable<StudentResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('courseId', courseId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }

  loadById(id: number): Observable<StudentResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  create(dto: StudentRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: StudentRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }
}
