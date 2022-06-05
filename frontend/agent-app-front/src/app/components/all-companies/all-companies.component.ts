import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from 'src/app/model/company';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.css']
})
export class AllCompaniesComponent implements OnInit {

  companies: Company[] = []
  currentUser: User
  constructor(public authService: AuthService, public companyService: CompanyService, public route: ActivatedRoute) { }

  ngOnInit(): void {
    this.authService.whoami().subscribe(data => this.currentUser = data);

    if (window.location.href.indexOf("/my-companies") > -1) {
      this.companyService.getCompaniesByOwner().subscribe(data => this.companies = data);
    }
    else
      this.getAllCompanies();
  }

  getAllCompanies() {
    this.companyService.getAllCompanies().subscribe(data => this.companies = data);
  }

  public logOut() {
    this.authService.logOut();
    window.location.href = "/login"
  }

  public openCompanyPage(companyId: string) {
    window.location.href = "/companies/" + companyId;
  }

}
