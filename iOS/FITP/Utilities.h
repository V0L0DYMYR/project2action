//
//  Utilities.h
//  QUIZ
//
//  Created by Eugene Braginets on 18.09.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//


#import <Foundation/Foundation.h>
#import "Reachability.h"

@interface Utilities : NSObject
+(NSString *) decodeFromURLString: (NSString*) input;
+(NSString *) encodeToURLString: (NSString*) input;
+ (void)msg: (NSString*) text;

+ (UIImageView*) roundCorners:(UIImageView*)image frameSize:(CGSize)size radius:(float)radius frameBorderThickness:(float) thickness;
+ (NSArray*)shuffleArray:(NSArray*)array;
+ (BOOL) isPad;
+(int) internetAvailable;
+ (CGRect)getScreenFrameForCurrentOrientation;
+ (CGRect)getScreenFrameForOrientation:(UIInterfaceOrientation)orientation;
void runOnMainQueueWithoutDeadlocking(void (^block)(void));
+ (int) version;
@end
