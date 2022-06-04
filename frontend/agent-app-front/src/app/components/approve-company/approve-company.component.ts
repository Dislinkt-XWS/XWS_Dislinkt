import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Company, Status } from 'src/app/model/company';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-approve-company',
  templateUrl: './approve-company.component.html',
  styleUrls: ['./approve-company.component.css']
})
export class ApproveCompanyComponent implements OnInit {

  companies: Company[] = [];
  selectedCompany: Company;

  constructor(public companyService: CompanyService, public matSnackBar: MatSnackBar, public authService: AuthService) { }

  ngOnInit(): void {
    this.getAllPending();
  }

  public getAllPending() {
    return this.companyService.getAllPending().subscribe(data => this.companies = data)
  }

  public selectCompany(company: Company) {
    this.selectedCompany = company;
  }

  public approveCompany() {
    this.companyService.approveCompany(this.selectedCompany.id, Status.APPROVED).subscribe(
      (data) => {
        this.matSnackBar.open("Company request approved!", "Dissmiss", {
          duration: 1000
        });

        setTimeout(() => {
          this.getAllPending();
        }, 200);
      },
      (error) => {
        this.matSnackBar.open("We were unable to approve this request!", "Dissmiss", {
          duration: 1000
        })
      }
    );
  }

  public declineCompany() {
    this.companyService.approveCompany(this.selectedCompany.id, Status.REJECTED).subscribe(
      (data) => {
        this.matSnackBar.open("Company request rejected!", "Dissmiss", {
          duration: 1000
        });

        setTimeout(() => {
          this.getAllPending();
        }, 200);
      },
      (error) => {
        this.matSnackBar.open("We were unable to reject this request!", "Dissmiss", {
          duration: 1000
        })
      }
    );
  }

  logOut() {
    this.authService.logOut();
    window.location.href = "/login";
  }

}
