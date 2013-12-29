



//
//  Utilities.m
//  QUIZ
//
//  Created by Eugene Braginets on 18.09.12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "Utilities.h"
#import <QuartzCore/QuartzCore.h>


@implementation Utilities
- (id)init {
    self = [super init];
    if (self) {
        // Custom initialization
    }
    return self;
}

+ (int) version {
    NSArray *ver = [[UIDevice currentDevice].systemVersion componentsSeparatedByString:@"."];
    return [[ver objectAtIndex:0] intValue];
}

+(NSString *) decodeFromURLString: (NSString*) input {
    NSString *result = [input stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    result = [result stringByReplacingOccurrencesOfString:@"+" withString:@" "];
    
    return result;
    
}   

+(NSString *) encodeToURLString: (NSString*) input {
    NSString* escapedUrlString =
    [input stringByAddingPercentEscapesUsingEncoding:
     NSUTF8StringEncoding];
    
    return escapedUrlString;
}


+ (void)msg: (NSString*) text {
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Notification:"
                                                    message:text
                                                   delegate:self 
                                          cancelButtonTitle:@"Ok" 
                                          otherButtonTitles:nil];
    [alert show];
}





+ (UIImageView*) roundCorners:(UIImageView*)image frameSize:(CGSize)size radius:(float)radius frameBorderThickness:(float) thickness{
    
    image.layer.cornerRadius=radius;
    image.layer.masksToBounds = YES;
    image.layer.borderColor = [UIColor blackColor].CGColor;
    image.layer.borderWidth = thickness;
    CGRect frame = image.frame;
    
    frame.size.width = size.width;
    frame.size.height = size.height;
    image.frame = frame;
    
    return image;
}

+ (NSArray*)shuffleArray:(NSArray*)array {
    
    NSMutableArray *temp = [[NSMutableArray alloc] initWithArray:array];
    
    for(NSUInteger i = [array count]; i > 1; i--) {
        NSUInteger j = arc4random_uniform(i);
        [temp exchangeObjectAtIndex:i-1 withObjectAtIndex:j];
    }
    
    return [NSArray arrayWithArray:temp];
}

+(int) internetAvailable {
    Reachability* internetReachable=[Reachability reachabilityForInternetConnection];
 
    NetworkStatus internetStatus = [internetReachable currentReachabilityStatus];
    
    switch (internetStatus)
    {
        case NotReachable:
        {
            NSLog(@"The internet is down.");
            return 0;
            break;
        }
        case ReachableViaWiFi:
        {
            NSLog(@"The internet is working via WIFI.");
            return 1;
            break;
        }
        case ReachableViaWWAN:
        {
            NSLog(@"The internet is working via WWAN.");
            return 2;
            break;
        }
    }

    
    return YES;
}

+ (BOOL) isPad {
#ifdef UI_USER_INTERFACE_IDIOM
    return (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad);
#else
    return NO;
#endif

}

+ (CGRect)getScreenFrameForCurrentOrientation {
    return [Utilities getScreenFrameForOrientation:[UIApplication sharedApplication].statusBarOrientation];
}

+ (CGRect)getScreenFrameForOrientation:(UIInterfaceOrientation)orientation {
    
    UIScreen *screen = [UIScreen mainScreen];
    CGRect fullScreenRect = screen.applicationFrame;
    
    //implicitly in Portrait orientation.
    if(orientation == UIInterfaceOrientationLandscapeRight || orientation == UIInterfaceOrientationLandscapeLeft){
        CGRect temp = CGRectZero;
        temp.size.width = fullScreenRect.size.height;
        temp.size.height = fullScreenRect.size.width;
        fullScreenRect = temp;
    }
    
    return fullScreenRect;
}



void runOnMainQueueWithoutDeadlocking(void (^block)(void))
{
    if ([NSThread isMainThread])
    {
        block();
    }
    else
    {
        dispatch_sync(dispatch_get_main_queue(), block);
    }
}

@end
