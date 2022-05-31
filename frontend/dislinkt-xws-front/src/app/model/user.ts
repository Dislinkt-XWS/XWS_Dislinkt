export interface UserDto {
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

export interface User {
    id: string;
    password: string;
    username: string;
    email: string;
    phoneNumber: string;
    userGender: UserGender;
    fullName: string;
    dateOfBirth: Date;
    bio: string;
    isPrivate: boolean;
    workExperience: string[],
    education: string[],
    skills: string[],
    interests: string[],
    followRequests: string[],
    followedUsers: string[],
    pendingFollowRequests: string[],
    followers: string[],
}

export enum UserGender {
    MALE, FEMALE, OTHER
}