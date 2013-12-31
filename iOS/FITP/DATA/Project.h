//
//  Project.h
//  P2A
//
//  Created by Eugene Braginets on 12/29/13.
//  Copyright (c) 2013 Eugene Braginets. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Project : NSObject
@property (copy, nonatomic) NSString *id_;
@property (copy, nonatomic) NSString *name;
@property (copy, nonatomic) NSString *description;
@property (copy, nonatomic) NSString *authorId;

@end
