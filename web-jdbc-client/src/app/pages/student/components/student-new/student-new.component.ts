import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {StudentApiService} from "../../../../service/student-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-student-new',
  templateUrl: './student-new.component.html',
  styleUrls: ['./student-new.component.scss']
})
export class StudentNewComponent implements OnInit {

  studentForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(),
    age: new FormControl(),
  });

  constructor(private _studentApiService: StudentApiService, private _router: Router, private _route: ActivatedRoute) { }

  ngOnInit(): void { }

  create(): void {
    this._studentApiService.create(this.studentForm.value).subscribe(() => {
      this._router.navigate(['/'], { relativeTo: this._route });
    });
  }

}
