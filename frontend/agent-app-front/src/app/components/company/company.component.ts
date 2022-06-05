import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Company } from 'src/app/model/company';
import { Experience, ExperienceLevel, ExperienceType, WorkPosition } from 'src/app/model/experience';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {
  company: Company

  isGeneral: boolean = true;
  isComments: boolean = false;
  isSalaries: boolean = false;
  isInterview: boolean = false;

  comments: Experience[]
  salaries: Experience[]
  interviews: Experience[]
  currentUser: User;
  companyId: string | null;

  workPosition: WorkPosition;
  experienceType: ExperienceType;
  experienceLevel: ExperienceLevel;
  textContent: string;
  salary: number;

  constructor(public companyService: CompanyService, public route: ActivatedRoute, public authService: AuthService) { }

  ngOnInit(): void {
    this.companyId = this.route.snapshot.paramMap.get('id');
    this.getAllComments();
    this.getAllInterviews();
    this.getAllSalaries();
    this.companyService.getCompanyById(this.companyId).subscribe(data => {
      this.company = data;
    });

    this.authService.whoami().subscribe(data => {
      this.currentUser = data;
    })
  }

  getAllComments() {
    this.companyService.findAllComments(this.companyId).subscribe(data => this.comments = data.sort((a, b) => new Date(b.datePosted).getTime() - new Date(a.datePosted).getTime()));
  }

  getAllSalaries() {
    this.companyService.findAllSalaries(this.companyId).subscribe(data => this.salaries = data.sort((a, b) => new Date(b.datePosted).getTime() - new Date(a.datePosted).getTime()));
  }

  getAllInterviews() {
    this.companyService.findAllInterviews(this.companyId).subscribe(data => this.interviews = data.sort((a, b) => new Date(b.datePosted).getTime() - new Date(a.datePosted).getTime()));
  }

  public logOut() {
    this.authService.logOut();
    window.location.href = "/login"
  }

  public showGeneral() {
    this.isGeneral = true;
    this.isComments = false;
    this.isSalaries = false;
    this.isInterview = false;
  }

  public showComments() {
    this.isComments = true;
    this.isGeneral = false;
    this.isSalaries = false;
    this.isInterview = false;
  }

  public showInterview() {
    this.isInterview = true;
    this.isComments = false;
    this.isSalaries = false;
    this.isGeneral = false;
  }

  public showSalaries() {
    this.isSalaries = true;
    this.isComments = false;
    this.isGeneral = false;
    this.isInterview = false;
  }

  comment() {
    let dto = {
      userId: this.currentUser.id,
      companyId: this.company.id,
      textContent: this.textContent,
      workPosition: this.workPosition,
      experienceLevel: this.experienceLevel,
      salary: this.salary,
      experienceType: ExperienceType.COMMENT
    }

    this.companyService.comment(dto).subscribe((data) => this.getAllComments());
  }

  addSalary() {
    let dto = {
      userId: this.currentUser.id,
      companyId: this.company.id,
      textContent: this.textContent,
      workPosition: this.workPosition,
      experienceLevel: this.experienceLevel,
      salary: this.salary,
      experienceType: ExperienceType.SALARY
    }

    this.companyService.comment(dto).subscribe((data) => this.getAllSalaries());
  }

  interview() {
    let dto = {
      userId: this.currentUser.id,
      companyId: this.company.id,
      textContent: this.textContent,
      workPosition: this.workPosition,
      experienceLevel: this.experienceLevel,
      salary: this.salary,
      experienceType: ExperienceType.INTERVIEW
    }

    this.companyService.comment(dto).subscribe((data) => this.getAllInterviews());
  }

  updateInfo() {
    this.companyService.update(this.company).subscribe();
  }

}
