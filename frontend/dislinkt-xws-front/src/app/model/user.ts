export interface User {
    password: string
    username: string;
    email: string;
    phoneNumber: string;
    userGender: UserGender;
    fullName: string;
    dateOfBirth: Date;
    bio: string;
    isPrivate: boolean;
}

export enum UserGender {
    MALE, FEMALE, OTHER
}