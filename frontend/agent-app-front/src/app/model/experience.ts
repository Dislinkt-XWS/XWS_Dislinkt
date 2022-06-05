export interface Experience {
    id: string
    userId: string
    companyId: string
    textContent: string
    workPosition: WorkPosition
    experienceLevel: ExperienceLevel
    salary: number
    experienceType: ExperienceType
    datePosted: Date
}

export enum WorkPosition {
    HR, BACKEND_DEVELOPER, FRONTEND_DEVELOPER, TESTER
}

export enum ExperienceLevel {
    JUNIOR, MEDIOR, SENIOR
}

export enum ExperienceType {
    COMMENT, INTERVIEW, SALARY
}