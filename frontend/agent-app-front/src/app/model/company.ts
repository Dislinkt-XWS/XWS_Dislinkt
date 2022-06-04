export interface Company {
    id: string
    ownerId: string
    name: string
    description: string
    yearOfEstablishment: number
    email: string
    phoneNumber: string
    address: string
    city: string
    country: string
    status: Status
}

export enum Status {
    APPROVED,
    REJECTED,
    PENDING
}

export interface CompanyDto {
    ownerId: string
    name: string
    description: string
    yearOfEstablishment: number
    email: string
    phoneNumber: string
    address: string
    city: string
    country: string
}