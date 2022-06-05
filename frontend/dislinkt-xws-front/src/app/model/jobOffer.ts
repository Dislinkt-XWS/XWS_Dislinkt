export interface JobOffer {
    id: string,
    position: string,
    jobDescription: string,
    requirements: string,
    companyId: string,
    isPublished: boolean
}

export interface JobOfferDTO {
    id: string,
    position: string,
    jobDescription: string,
    requirements: string,
    publisherId: string,
    companyId: string,
    isPublished: boolean
}