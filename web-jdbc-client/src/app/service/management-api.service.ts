import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import { appConst } from '../app.const';
import {ApiService} from "./api.service";
import {ManagementRequestDto} from "../model/management-request-dto";
import {ManagementResponseDto} from "../model/management-response-dto";
import {Observable} from "rxjs";
import {HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ManagementApiService {

  private _apiUrl = environment.apiUrl + appConst.managementsPath;

  constructor(private _apiService: ApiService<ManagementRequestDto, ManagementResponseDto>) { }

  loadAll(): Observable<ManagementResponseDto[]> {
    return this._apiService.loadAll(this._apiUrl);
  }

  loadAllByCourse(courseId: string): Observable<ManagementResponseDto[]> {
    let httpParams = new HttpParams();
    httpParams = httpParams.append('courseId', courseId);
    return this._apiService.loadAllByParams(this._apiUrl, httpParams);
  }

  loadById(id: number): Observable<ManagementResponseDto> {
    return this._apiService.loadById(this._apiUrl, id);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }

  create(dto: ManagementRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dto);
  }

  update(id: number, dto: ManagementRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dto);
  }
}
