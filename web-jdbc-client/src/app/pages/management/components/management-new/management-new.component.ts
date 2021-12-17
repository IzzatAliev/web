import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {ManagementApiService} from "../../../../service/management-api.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-management-new',
  templateUrl: './management-new.component.html',
  styleUrls: ['./management-new.component.scss']
})
export class ManagementNewComponent implements OnInit {

  managementForm = new FormGroup({
    name: new FormControl(''),
    staffCount: new FormControl('')
  });

  constructor(private _managementApiService: ManagementApiService, private _router: Router, private _route: ActivatedRoute) { }

  ngOnInit(): void { }

  create(): void {
    this._managementApiService.create(this.managementForm.value).subscribe(() => {
      this._router.navigate(['/'], { relativeTo: this._route });
    });
  }
}
